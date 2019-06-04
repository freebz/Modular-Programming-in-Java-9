package packt.addressbook.ui;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import packt.contact.model.*;
import packt.contact.util.*;
import packt.util.*;

public class Main extends Application {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        List<Contact> contacts = new ArrayList<>();
        ContactLoader contactLoader = new ContactLoader();
        try {
            contacts = contactLoader.loadContacts("../input.xml");
        } catch (ContactLoadException e) {
            logger.severe(e.getMessage());
            System.exit(0);
        }
        Iterable<SortUtil> sortUtils = ServiceLoader.load(SortUtil.class);
        for (SortUtil sortUtil : sortUtils) {
			sortUtil.sortList(contacts);
		}
        primaryStage.setTitle("Addressbook Viewer");

        BorderPane root = new BorderPane();
        HBox hbox = generateTitleBox();
        root.setTop(hbox);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        Label name = new Label();
        name.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        GridPane.setHalignment(name, HPos.RIGHT);
        grid.add(name, 0, 1);

        Label phone = new Label();
        phone.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
        GridPane.setHalignment(phone, HPos.RIGHT);
        grid.add(phone, 0, 2);

        Label addressLabel = new Label();
        addressLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        GridPane.setHalignment(addressLabel, HPos.RIGHT);
        grid.add(addressLabel, 0, 3);

        Label street = new Label();
        GridPane.setHalignment(street, HPos.RIGHT);
        grid.add(street, 0, 4);

        Label city = new Label();
        GridPane.setHalignment(city, HPos.RIGHT);
        grid.add(city, 0, 5);

        Label state = new Label();
        GridPane.setHalignment(state, HPos.RIGHT);
        grid.add(state, 0, 6);

        Label country = new Label();
        GridPane.setHalignment(country, HPos.RIGHT);
        grid.add(country, 0, 7);


        final List<Contact> finalContactList = contacts;
        // 새로운 자바 FX ListView 생성
        ListView<String> list = new ListView<String>();
        // 성, 이름 형식으로 주소록의 이름 목록 수집
        List<String> listContactNames = contacts.stream()
            .map(c -> c.getLastName() + ", " + c.getFirstName())
            .collect(Collectors.toList());
        // 이름 목록으로부터 ObservableList 생성
        ObservableList<String> obsContactNames =
            FXCollections.observableList(listContactNames);
        // 그것을 ListView에 넘겨 목록에 표시
        list.setItems(obsContactNames);
        // 클릭 이벤트 리스너 등록
        list.getSelectionModel()
            .selectedItemProperty()
            .addListener((obs, oldVal, newVal) -> {
                // ListView의 선택된 인덱스를 가져옴
                int selectedIndex = list.getSelectionModel().getSelectedIndex();
                name.setText(newVal);
                // 클릭한 Contact 인스턴스를 가져옴
                Contact contact = finalContactList.get(selectedIndex);
                // 우측에 내용 표시
                phone.setText("Phone: " + contact.getPhone());
                addressLabel.setText("Address");
                street.setText(contact.getAddress().getStreet());
                city.setText(contact.getAddress().getCity());
                state.setText(contact.getAddress().getState());
                country.setText(contact.getAddress().getCountry());
        });
        
        list.setPrefWidth(300);
        list.setPrefHeight(650);
        root.setLeft(list);
        root.setRight(grid);

        primaryStage.setScene(new Scene(root, 700, 550));
        primaryStage.show();
    }

    public HBox generateTitleBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Label appTitle = new Label("Addressbook Reader");
        appTitle.setTextFill(Color.web("#FFFFFF"));
        appTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        hbox.getChildren().add(appTitle);

        return hbox;
    }
}
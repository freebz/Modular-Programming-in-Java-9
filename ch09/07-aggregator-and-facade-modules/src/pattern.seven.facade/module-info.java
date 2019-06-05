module pattern.seven.facade {
    requires transitive module.one;
    requires transitive module.two;
    exports pattern.seven.external;
}
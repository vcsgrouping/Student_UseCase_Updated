/* perform any action on widgets/variables within this block */

Partial.onReady = function () {
    /*
     * variables can be accessed through 'Partial.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Partial.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Partial.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Partial.Widgets.username.datavalue'
     */

};

Partial.liveform_academicYear_startYearBlur = function ($event, widget) {
    var acStartYear = Partial.Widgets.startYear.datavalue;
    Partial.Widgets.endYear.datavalue = acStartYear + 1;
    Partial.Widgets.academicYear.datavalue = acStartYear + '-' + Partial.Widgets.endYear.datavalue;
};


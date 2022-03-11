/* perform any action on widgets/variables within this block */

Page.onReady = function () {
    /*
     * variables can be accessed through 'Page.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Page.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */

};

Page.liveform_Results_academicTestSubjectsChange = function ($event, widget, newVal, oldVal) {
    //debugger;
    // Page.Variables.StVar_MarksSecured.dataSet.marksSecured = Page.Widgets.marksSecured.datavalue;
    // Page.Variables.StVar_MarksSecured.dataSet.academicTestSubjects = Page.Widgets.academicTestSubjects.datavalue;
    var acheivedMarks = Page.Widgets.marksSecured.datavalue;
    var maxmarks = Page.Widgets.academicTestSubjects.datavalue.maxMarks;
    var percentage = (acheivedMarks / maxmarks) * 100;
    var minval = Math.floor(percentage / 10);
    minval = minval * 10;
    Page.Variables.LV_GradesByMinValue.setFilter('minValue', minval);
    Page.Variables.LV_GradesByMinValue.listRecords({}, function () {
        Page.Widgets.gradeDetails.datavalue = Page.Variables.LV_GradesByMinValue.dataSet[0];
    });

};


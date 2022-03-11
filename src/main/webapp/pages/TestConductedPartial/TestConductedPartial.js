var wmToaster = App.getDependency("ToasterService");
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

Partial.dialog2_grid_UpdateResultsRowclick = function ($event, $rowData) {
    console.log('grid_UpdateResultsRowclick');
};

Partial.grid_UpdateResults_marksSecuredOnChange = function ($event, widget, rowData) {
    debugger;
    var marksSecuredValue = new RegExp(/^\d{2}$/);
    if (marksSecuredValue.test(widget.datavalue)) {
        var acheivedMarks = widget.datavalue;
        var maxmarks = Partial.Widgets.grid_TestConducted.dataset.data[0].academicTestSubjects.maxMarks;
        var percentage = (acheivedMarks / maxmarks) * 100;
        var minval = Math.floor(percentage / 10);
        minval = minval * 10;
        Partial.Variables.LV_GradesByMinValue.listRecords({}, function () {
            var gradeData = Partial.Variables.LV_GradesByMinValue.getData();
            var gradeDataLength = gradeData.data.length;
            for (var i = 0; i < gradeDataLength; i++) {
                if (minval >= gradeData.data[i].minValue && minval <= gradeData.data[i].maxValue) {
                    minval = gradeData.data[i].minValue;
                }
            }
            Partial.Variables.LV_GradesByMinValue.setFilter('minValue', minval);
            Partial.Variables.LV_GradesByMinValue.listRecords({}, function () {
                Partial.Widgets.grid_UpdateResults.formfields['gradeDetails.gradeId'].setProperty('value', Partial.Variables.LV_GradesByMinValue.dataSet[0].gradeId);
                Partial.Widgets.grid_UpdateResults.formfields['gradeDetails.grade'].setProperty('value', Partial.Variables.LV_GradesByMinValue.dataSet[0].grade);
            });

        });

    } else {
        wmToaster.show('error', 'ERROR', 'Please Enter Valid Marks', 5000);
    }
};

// Partial.presenceOnChange = function($event, widget, rowData) {
//     debugger;
//     Partial.Widgets.grid_UpdateResults.columns.marksSecured.disabled = !widget.datavalue;
// };

// Partial.presenceOnBlur = function($event, widget, rowData) {
//     debugger;
//     Partial.Widgets.grid_UpdateResults.columns.marksSecured.disabled = !widget.datavalue;
// };


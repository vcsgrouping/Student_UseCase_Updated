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

Partial.liveform_gradeDetails_minValueChange = function ($event, widget, newVal, oldVal) {
    if (Partial.Widgets.liveform_gradeDetails.operationType == "insert") {
        var gradeData = Partial.Variables.School_DB_GradeDetailsData.dataSet;
        var len = gradeData.length;
        for (var i = 0; i < len; i++) {
            var gradeMinValue = gradeData[i].minValue;
            var gradeMaxValue = gradeData[i].maxValue;
            var gradeId = gradeData[i].gradeId;
            if (Partial.Widgets.gradeId.datavalue != gradeId) {
                if (Partial.Widgets.minValue.datavalue >= gradeMinValue && Partial.Widgets.minValue.datavalue <= gradeMaxValue) {
                    Partial.Widgets.minValue.setValidationMessage('Entered Value ' + Partial.Widgets.minValue.datavalue + ' overlaps in Grade: ' + gradeData[i].grade);
                    Partial.Widgets.save.disabled = true;
                    break;
                } else if (Partial.Widgets.minValue.datavalue == null) {
                    Partial.Widgets.save.disabled = true;
                    break;
                } else {
                    Partial.Widgets.save.disabled = false;
                    continue;
                }
            } else if (Partial.Widgets.gradeId.datavalue == gradeId) {
                if (Partial.Widgets.minValue.datavalue == Partial.Widgets.maxValue.datavalue) {
                    Partial.Widgets.minValue.setValidationMessage('Entered Value ' + Partial.Widgets.minValue.datavalue + ' is Equal to ' + Partial.Widgets.maxValue.datavalue);
                    Partial.Widgets.save.disabled = true;
                    break;
                }
            }
        }
    } else if (Partial.Widgets.liveform_gradeDetails.operationType == "update") {
        var gradeData1 = Partial.Variables.School_DB_GradeDetailsData.dataSet;
        var len1 = gradeData1.length;
        for (var i1 = 0; i1 < len1 - 1; i1++) {
            var gradeMinValue1 = gradeData1[i1].minValue;
            var gradeMaxValue1 = gradeData1[i1].maxValue;
            var gradeId1 = gradeData1[i1].gradeId;
            if (Partial.Widgets.gradeId.datavalue != gradeId1) {
                if (Partial.Widgets.minValue.datavalue >= gradeMinValue1 && Partial.Widgets.minValue.datavalue <= gradeMaxValue1) {
                    Partial.Widgets.minValue.setValidationMessage('Entered Value ' + Partial.Widgets.minValue.datavalue + ' overlaps in Grade: ' + gradeData1[i1].grade);
                    Partial.Widgets.save.disabled = true;
                }
            } else if (Partial.Widgets.gradeId.datavalue == gradeId1) {
                debugger;
                if (Partial.Widgets.minValue.datavalue == Partial.Widgets.maxValue.datavalue) {
                    Partial.Widgets.minValue.setValidationMessage('Entered Value ' + Partial.Widgets.minValue.datavalue + ' is Equal to ' + Partial.Widgets.maxValue.datavalue);
                    Partial.Widgets.save.disabled = true;
                    break;
                }
            }
        }
    }
};

Partial.liveform_gradeDetails_maxValueChange = function ($event, widget, newVal, oldVal) {
    if (Partial.Widgets.liveform_gradeDetails.operationType == "insert") {
        var gradeData = Partial.Variables.School_DB_GradeDetailsData.dataSet;
        var len1 = gradeData.length;
        for (var j = 0; j < len1; j++) {
            var gradeMinValue1 = gradeData[j].minValue;
            var gradeMaxValue1 = gradeData[j].maxValue;
            var gradeId = gradeData[j].gradeId;
            if (Partial.Widgets.gradeId.datavalue != gradeId) {
                if (Partial.Widgets.maxValue.datavalue >= gradeMinValue1 && Partial.Widgets.maxValue.datavalue <= gradeMaxValue1) {
                    Partial.Widgets.maxValue.setValidationMessage('Entered Value ' + Partial.Widgets.maxValue.datavalue + ' overlaps in Grade: ' + gradeData[j].grade);
                    Partial.Widgets.save.disabled = true;
                    break;
                } else if (Partial.Widgets.maxValue.datavalue == null) {
                    Partial.Widgets.maxValue.setValidationMessage('Value cannot be null');
                    Partial.Widgets.save.disabled = true;
                    break;
                } else if (Partial.Widgets.maxValue.datavalue <= Partial.Widgets.minValue.datavalue) {
                    Partial.Widgets.maxValue.setValidationMessage('Entered Value ' + Partial.Widgets.maxValue.datavalue + ' is less than or Equal to ' + Partial.Widgets.minValue.datavalue);
                    Partial.Widgets.save.disabled = true;
                    break;
                } else {
                    Partial.Widgets.save.disabled = false;
                    continue;
                }
            } else if (Partial.Widgets.gradeId.datavalue == gradeId) {
                if (Partial.Widgets.minValue.datavalue == Partial.Widgets.maxValue.datavalue) {
                    Partial.Widgets.maxValue.setValidationMessage('Entered Value ' + Partial.Widgets.minValue.datavalue + ' is Equal to ' + Partial.Widgets.maxValue.datavalue);
                    Partial.Widgets.save.disabled = true;
                    break;
                }
            }
        }
    } else if (Partial.Widgets.liveform_gradeDetails.operationType == "update") {
        debugger;
        var gradeData1 = Partial.Variables.School_DB_GradeDetailsData.dataSet;
        var len2 = gradeData1.length;
        for (var i1 = 0; i1 < len2 - 1; i1++) {
            var gradeMinValue2 = gradeData1[i1].minValue;
            var gradeMaxValue2 = gradeData1[i1].maxValue;
            var gradeId1 = gradeData1[i1].gradeId;
            if (Partial.Widgets.gradeId.datavalue != gradeId1) {
                if (Partial.Widgets.maxValue.datavalue >= gradeMinValue2 && Partial.Widgets.maxValue.datavalue <= gradeMaxValue2) {
                    Partial.Widgets.maxValue.setValidationMessage('Entered Value ' + Partial.Widgets.maxValue.datavalue + ' overlaps in Grade: ' + gradeData1[i1].grade);
                    Partial.Widgets.save.disabled = true;
                } else {
                    Partial.Widgets.save.disabled = false;
                    continue;
                }
            } else if (Partial.Widgets.gradeId.datavalue == gradeId) {
                if (Partial.Widgets.minValue.datavalue == Partial.Widgets.maxValue.datavalue) {
                    Partial.Widgets.maxValue.setValidationMessage('Entered Value ' + Partial.Widgets.minValue.datavalue + ' is Equal to ' + Partial.Widgets.maxValue.datavalue);
                    Partial.Widgets.save.disabled = true;
                    break;
                }
            }
        }
    }
};


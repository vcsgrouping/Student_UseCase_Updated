Application.$controller("Grade_DetailsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };

}]);


Application.$controller("grid_gradeDetailsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform_gradeDetailsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.minValueChange = function($event, $isolateScope, newVal, oldVal) {
            if ($scope.operationType == "insert") {
                var gradeData = $scope.Variables.School_DB_GradeDetailsData.dataSet.data;
                var len = gradeData.length;
                for (var i = 0; i < len; i++) {
                    var gradeMinValue = gradeData[i].minValue;
                    var gradeMaxValue = gradeData[i].maxValue;
                    var gradeId = gradeData[i].gradeId;
                    if ($scope.Widgets.gradeId.datavalue != gradeId) {
                        if ($scope.Widgets.minValue.datavalue >= gradeMinValue && $scope.Widgets.minValue.datavalue <= gradeMaxValue) {
                            $scope.Widgets.minValue.setValidationMessage('Entered Value ' + $scope.Widgets.minValue.datavalue + ' overlaps in Grade: ' + gradeData[i].grade);
                            $scope.Widgets.save.disabled = true;
                            break;
                        } else if ($scope.Widgets.minValue.datavalue == null) {
                            $scope.Widgets.save.disabled = true;
                            break;
                        } else {
                            $scope.Widgets.save.disabled = false;
                            continue;
                        }
                    } else if ($scope.Widgets.gradeId.datavalue == gradeId) {
                        if ($scope.Widgets.minValue.datavalue == $scope.Widgets.maxValue.datavalue) {
                            $scope.Widgets.minValue.setValidationMessage('Entered Value ' + $scope.Widgets.minValue.datavalue + ' is Equal to ' + $scope.Widgets.maxValue.datavalue);
                            $scope.Widgets.save.disabled = true;
                            break;
                        }
                    }
                }
            } else if ($scope.operationType == "update") {
                var gradeData1 = $scope.Variables.School_DB_GradeDetailsData.dataSet.data;
                var len1 = gradeData1.length;
                for (var i1 = 0; i1 < len1 - 1; i1++) {
                    var gradeMinValue1 = gradeData1[i1].minValue;
                    var gradeMaxValue1 = gradeData1[i1].maxValue;
                    var gradeId1 = gradeData1[i1].gradeId;
                    if ($scope.Widgets.gradeId.datavalue != gradeId1) {
                        if ($scope.Widgets.minValue.datavalue >= gradeMinValue1 && $scope.Widgets.minValue.datavalue <= gradeMaxValue1) {
                            $scope.Widgets.minValue.setValidationMessage('Entered Value ' + $scope.Widgets.minValue.datavalue + ' overlaps in Grade: ' + gradeData1[i1].grade);
                            $scope.Widgets.save.disabled = true;
                        }
                    } else if ($scope.Widgets.gradeId.datavalue == gradeId1) {
                        debugger
                        if ($scope.Widgets.minValue.datavalue == $scope.Widgets.maxValue.datavalue) {
                            $scope.Widgets.minValue.setValidationMessage('Entered Value ' + $scope.Widgets.minValue.datavalue + ' is Equal to ' + $scope.Widgets.maxValue.datavalue);
                            $scope.Widgets.save.disabled = true;
                            break;
                        }
                    }
                }
            }
        };

        $scope.maxValueChange = function($event, $isolateScope, newVal, oldVal) {
            if ($scope.operationType == "insert") {
                var gradeData = $scope.Variables.School_DB_GradeDetailsData.dataSet.data;
                var len1 = gradeData.length;
                for (var j = 0; j < len1; j++) {
                    var gradeMinValue1 = gradeData[j].minValue;
                    var gradeMaxValue1 = gradeData[j].maxValue;
                    var gradeId = gradeData[j].gradeId;
                    if ($scope.Widgets.gradeId.datavalue != gradeId) {
                        if ($scope.Widgets.maxValue.datavalue >= gradeMinValue1 && $scope.Widgets.maxValue.datavalue <= gradeMaxValue1) {
                            $scope.Widgets.maxValue.setValidationMessage('Entered Value ' + $scope.Widgets.maxValue.datavalue + ' overlaps in Grade: ' + gradeData[j].grade);
                            $scope.Widgets.save.disabled = true;
                            break;
                        } else if ($scope.Widgets.maxValue.datavalue == null) {
                            $scope.Widgets.maxValue.setValidationMessage('Value cannot be null');
                            $scope.Widgets.save.disabled = true;
                            break;
                        } else if ($scope.Widgets.maxValue.datavalue <= $scope.Widgets.minValue.datavalue) {
                            $scope.Widgets.maxValue.setValidationMessage('Entered Value ' + $scope.Widgets.maxValue.datavalue + ' is less than or Equal to ' + $scope.Widgets.minValue.datavalue);
                            $scope.Widgets.save.disabled = true;
                            break;
                        } else {
                            $scope.Widgets.save.disabled = false;
                            continue;
                        }
                    } else if ($scope.Widgets.gradeId.datavalue == gradeId) {
                        if ($scope.Widgets.minValue.datavalue == $scope.Widgets.maxValue.datavalue) {
                            $scope.Widgets.maxValue.setValidationMessage('Entered Value ' + $scope.Widgets.minValue.datavalue + ' is Equal to ' + $scope.Widgets.maxValue.datavalue);
                            $scope.Widgets.save.disabled = true;
                            break;
                        }
                    }
                }
            } else if ($scope.operationType == "update") {
                debugger
                var gradeData1 = $scope.Variables.School_DB_GradeDetailsData.dataSet.data;
                var len2 = gradeData1.length;
                for (var i1 = 0; i1 < len2 - 1; i1++) {
                    var gradeMinValue2 = gradeData1[i1].minValue;
                    var gradeMaxValue2 = gradeData1[i1].maxValue;
                    var gradeId1 = gradeData1[i1].gradeId;
                    if ($scope.Widgets.gradeId.datavalue != gradeId1) {
                        if ($scope.Widgets.maxValue.datavalue >= gradeMinValue2 && $scope.Widgets.maxValue.datavalue <= gradeMaxValue2) {
                            $scope.Widgets.maxValue.setValidationMessage('Entered Value ' + $scope.Widgets.maxValue.datavalue + ' overlaps in Grade: ' + gradeData1[i1].grade);
                            $scope.Widgets.save.disabled = true;
                        } else {
                            $scope.Widgets.save.disabled = false;
                            continue;
                        }
                    } else if ($scope.Widgets.gradeId.datavalue == gradeId) {
                        if ($scope.Widgets.minValue.datavalue == $scope.Widgets.maxValue.datavalue) {
                            $scope.Widgets.maxValue.setValidationMessage('Entered Value ' + $scope.Widgets.minValue.datavalue + ' is Equal to ' + $scope.Widgets.maxValue.datavalue);
                            $scope.Widgets.save.disabled = true;
                            break;
                        }
                    }
                }

            }
        };
    }
]);
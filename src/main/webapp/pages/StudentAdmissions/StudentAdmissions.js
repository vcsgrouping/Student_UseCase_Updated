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

    var x = Page.Variables.School_DBStudentDetailsData.listRecords({}, function () {
        console.log(x);
        var studentData = Page.Variables.School_DBStudentDetailsData.dataSet;
        var studentLength = studentData.length;
        var i = 0;
        for (i = 0; i < studentLength; i++) {
            Page.Variables.StVar_StudentData.dataSet.push(studentData[i]);
        }
    });

};

Page.dialog1_liveform_StudentDetailsSuccess = function ($event, $operation, $data) {
    Page.Variables.School_DBStudentDetailsData.listRecords({}, function (newData) {
        debugger;
        console.log(newData);
        var studentData = Page.Variables.School_DBStudentDetailsData.getData();
        var studentLength = studentData.data.length;
        //var studentLength = newData.length;
        Page.Variables.StVar_StudentData.dataSet.splice(1, studentLength);
        var i = 0;
        for (i = 0; i < studentLength; i++) {
            Page.Variables.StVar_StudentData.dataSet.push(studentData.data[i]);
        }
    });

};

Page.liveform_StudentDetails_currentDay = moment().format("YYYY-MM-DD");

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

// Partial.School_DBAcademicsDataonSuccess = function(variable, data) {
//     debugger;
//     console.log(data);
//     var studentAcademics = Partial.Variables.School_DBStudentAcademics.dataSet.data;
//     console.log(studentAcademics);
//     var length = studentAcademics.length;
//     var count = 0;
//     for (var i = 0; i < length; i++) {
//         var count = _.countBy(studentAcademics[i].standardId);
//         count++;
//         // var length=studentAcademics.length;
//         // var count=0;
//         // for(var i=0; i<length;i++){
//     }
// };

Partial.School_DBStudentAcademicsonSuccess = function (variable, data) {
    debugger;
    var studentAcademics = Partial.Variables.School_DBStudentAcademics.dataSet.data;
    console.log(studentAcademics);
    var length = studentAcademics.length;
    // var count = 0;
    // for (var i = 0; i < length; i++) {
    //     var count = _.countBy(studentAcademics[i].standardId);
    //     count++;
    //     // var length=studentAcademics.length;
    // }
};


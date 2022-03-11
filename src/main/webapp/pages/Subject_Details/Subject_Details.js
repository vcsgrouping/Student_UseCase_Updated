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


function subjectData() {
    if (Partial.Widgets.txt_subjectName.datavalue != null) {
        Partial.Variables.LV_InsertSubjectDetails.createRecord();
        wmToaster.show('success', 'SUCCESS', 'Record Added successfully', 9000);
        subjectTextHide();
    } else if (Partial.Widgets.txt_subjectName.datavalue == null) {
        wmToaster.show('error', 'ERROR', 'Subject Name cannot be null', 9000);
        subjectTextShow();
    }
}

function subjectTextHide() {
    Partial.Widgets.txt_subjectName.datavalue = '';
    Partial.Widgets.composite_subjectName.show = false;
    Partial.Widgets.button_addNew.show = false;
    Partial.Widgets.button_cancel.show = false;
    Partial.Widgets.button_addsubject.show = true;
}

function subjectTextShow() {
    Partial.Widgets.composite_subjectName.show = true;
    Partial.Widgets.button_addNew.show = true;
    Partial.Widgets.button_cancel.show = true;
    Partial.Widgets.button_addsubject.show = false;
}
Partial.button_addsubjectClick = function ($event, widget) {
    subjectTextShow();
};

Partial.txt_subjectNameKeydown = function ($event, widget) {
    if ($event.keyCode === 13) {
        subjectData();
    }
};

Partial.button_cancelClick = function ($event, widget) {
    subjectTextHide();
};

Partial.button_addNewClick = function ($event, widget) {
    subjectData();
};

Partial.LV_InsertSubjectDetailsonError = function (variable, data) {
    subjectTextShow();
    wmToaster.show('error', 'ERROR', 'Subject Already Exists. Please try again!', 9000);
};


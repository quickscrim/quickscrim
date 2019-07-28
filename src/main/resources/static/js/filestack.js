"use strict";

console.log("moo-moo.");

const client = filestack.init(filestackApiKey);

const options = {
    onFileUploadFinished: file => {

        const profilePicUrl = file.url;

        let fileObj = {
            'type': 'hidden',
            'value': profilePicUrl,
            'name': 'profilePicUrl'
        };

        $('<input/>').attr(fileObj).appendTo("#pfpUpload");
        $('#pfpUpload').submit();


    }
};

client.picker(options).open();


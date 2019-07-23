"use strict";

console.log("moo-moo.");

const client = filestack.init(filestackApiKey);

const options = {
    onFileUploadFinished: file => {

        const profilePicUrl = file.url;

        $('<input/>').attr({'type': 'hidden', 'value': file.url, 'name': 'profilePicUrl'}).appendTo("#pfpUpload");
        $('#pfpUpload').submit();


    }
};

client.picker(options).open();


"use strict";

console.log("moo-moo.");

const client = filestack.init(filestackApiKey);

const options = {
    onFileUploadFinished: file => {
        console.log("I did it!");
        }
    };

client.picker(options).open();
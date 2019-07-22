"use strict";

console.log("moo-moo.");

const client = filestack.init(filestackApiKey);

const options = {
    onFileUploadFinished: file => {
        console.log(file.url);
        const profilePicUrl = file.url;

        $.ajax({
            type: "POST",
            url: "/upload/pic",
            data: {profilePicUrl: profilePicUrl}
        });

        function storeUrl(url) {
            // take the profile pic url
        }
        }


    };

client.picker(options).open();


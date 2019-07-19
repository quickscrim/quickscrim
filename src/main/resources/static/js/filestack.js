"use strict";

console.log("moo-moo.");

const client = filestack.init(filestackApiKey);
client.picker().open();

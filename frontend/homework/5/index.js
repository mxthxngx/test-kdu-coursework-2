const os = require('os')

/**
 * Retrieves and returns the specifications of the operating system and hardware.
 *
 * @return {object} - The specifications of the operating system and hardware.
 */
function getOSSpecs()
{
    let specs = {
        hostname:os.hostname(),
        operatingSystem: os.platform(),
        architecture: os.arch(),
        release: os.release(),
        uptime: os.uptime(),
        numberOfCores: os.cpus(),
        totalMemory: os.totalmem(),
        freeMemory: os.freemem(),
        currentWorkingDirectory:process.cwd()
    }
    return specs;
}
let jsonObject = getOSSpecs()
console.log(jsonObject);
/**
 * Write the given JSON object into a file.
 *
 * @param {Object} jsonObject - the JSON object to be written into the file
 * @return {void} 
 */
function writeIntoFile(jsonObject)
{
const fs = require("fs")
fs.writeFile("json_text.json",JSON.stringify(jsonObject),err => 
{
    if(err) throw err;
    console.log("Writing complete.");
})
}

writeIntoFile(jsonObject);
/**
 * Extracts file information from the given file path.
 *
 * @param {string} filePath - the path of the file
 * @return {object} fileDetails - an object containing file details such as extension, filename, and directory name
 */

function extractFileInfo(filePath)
{
const path = require('path')

 let fileDetails = {
    extension:path.extname(filePath),
    filename:path.basename(filePath,path.extname(filePath)),
    directoryName:path.dirname(filePath)
}
console.log(fileDetails);
return fileDetails;
}

/**
 * Process file paths and extract file info.
 *
 * @param {object} fileDetails - details of files to be processed
 * @return {array} array of file information
 */
function ProcessFilePaths(fileDetails)
{
    let fileInfo = [];
    for(let file in  fileDetails)
    {
        fileInfo.push(extractFileInfo(fileDetails[file]));
    }
    return fileInfo;
}
let arrayOfFiles = [
    'dir1/dir2/file1.txt',
    'dir1/dir3/file2.txt',
    'dir1/dir3/file3.md',
    'dir4/file4.jpg',
    'dir4/file5.pdf',
    ];
console.log(JSON.stringify(ProcessFilePaths(arrayOfFiles)));
let http = require('http')
http.createServer(function(req,res)
{
    res.writeHead(200, {'Content-Type': 'text/json'});
    res.write("Hello my name is Mathangi and here is my system information"+JSON.stringify(jsonObject));
    res.end();
}).listen(8000)
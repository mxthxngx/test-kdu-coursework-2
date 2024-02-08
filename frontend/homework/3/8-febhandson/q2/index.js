/**
 * Trim and convert the given element to its abbreviated form for days of the week.
 *
 * @param {string} element - the element to be trimmed and converted
 * @return {string} the abbreviated form of the given element
 */
function trimmedElemen(element)
{
    let trimmedElement = element.trim();
    switch(trimmedElement)
    {
        case "Sunday": trimmedElement = "SUN";break;
        case "Monday": trimmedElement = "MON";break;
        case "Tuesday": trimmedElement = "TUE";break;
        case "Wednesday": trimmedElement = "WED";break;
        case "Thursday": trimmedElement = "THU";break;
        case "Friday": trimmedElement = "FRI";break;
        case "Saturday": trimmedElement = "SAT";break;
        default : trimmedElement = "INVALID"
    }
    return trimmedElement;
}

function codedString(element)
{
    element = element.trim();
    for(let i = 0; i<element.length;i++)
    {
        switch(element.charAt(i))
        {
            case "a": element = element.replace(element.charAt(i),"4");break;
            case "e": element = element.replace(element.charAt(i),"3");break;
            case "o": element = element.replace(element.charAt(i),"0");break;
            case "s": element = element.replace(element.charAt(i),"5");break;
            default: element = element
        }
    }
    return element;
}

let stringArray=["Sunday "," Monday ", " Tuesday","Wednesday "," Thursday "," Friday","Saturday "]
stringArray = stringArray.map(element => trimmedElemen(element));

console.log(stringArray)


let input1 = "javascript is cool"
console.log(codedString(input1))

let input2 = "   programming is fun" 
console.log(codedString(input2))

let input3 = " become a coder"
console.log(codedString(input3))
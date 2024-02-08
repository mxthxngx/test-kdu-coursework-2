let inputString = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';

let jsonObject = JSON.parse(inputString);

Object.keys(jsonObject).forEach(function(key){
    if (key !== "email" && typeof jsonObject[key] === 'string') {
        jsonObject[key] = jsonObject[key].toUpperCase();
    }
});



console.log(jsonObject);
delete jsonObject.email;

let finalString = JSON.stringify(jsonObject);
console.log(finalString);

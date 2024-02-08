const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
        country: "Spain",
        city: "Barcelona",
    },
    careerInfo: {
        fcBarcelona: {
            appearances: 780,
            goals: {
                premierLeagueGoals: 590,
                championsLeagueGoals: 50,
            },
        },
    },
};

console.log("Only keys:");
console.log(getAllKeys(player));

console.log("Only values:");
console.log(getAllValues(player));

function getAllKeys(obj) {
    let keys = [];

    for (let key in obj) {
        keys.push(key);
        if (typeof obj[key] === 'object') {
            keys = keys.concat(getAllKeys(obj[key]));
        }
    }

    return keys;
}

function getAllValues(obj) {
    let values = [];

    for (let key in obj) {
        if (typeof obj[key] === 'object') {
            values = values.concat(getAllValues(obj[key]));
        } else {
            values.push(obj[key]);
        }
    }

    return values;
}

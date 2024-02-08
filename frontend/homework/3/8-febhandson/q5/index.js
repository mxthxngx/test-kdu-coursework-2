player = {
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
console.log("Only keys")
console.log(Object.keys(player));


const getObjectValues = (obj) => (obj && typeof obj === 'object')
 ?Object.values(obj).map(getObjectValues).flat()
 : [obj]
console.log("Only values")
console.log(getObjectValues(player));

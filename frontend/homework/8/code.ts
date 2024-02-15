interface Recipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    difficulty: string;
    ingredients: string[];
    timeTaken: number;
    calorieCount: number;
}

async function fetchRecipesFromAPI(): Promise<Recipe[]> {
    const response = await fetch("https://dummyjson.com/recipes");
    const data = await response.json();
    const recipes: Recipe[] = data.recipes.map((item: any) => ({
        image: item.image,
        name: item.name,
        rating: item.rating,
        cuisine: item.cuisine,
        ingredients: item.ingredients,
        difficulty: item.difficulty,
        timeTaken: item.prepTimeMinutes + item.cookTimeMinutes,
        calorieCount: item.caloriesPerServing
    }));
    return recipes;
}

async function searchRecipes(query: string): Promise<Recipe[]> {
    console.log(query);
    let str = `https://dummyjson.com/recipes/search?q=${query}`;
console.log(str);

    const response = await fetch(str);
    const data = await response.json();
    const rec: Recipe[] = data.recipes.map((item: any) => ({
        image: item.image,
        name: item.name,
        rating: item.rating,
        cuisine: item.cuisine,
        ingredients: item.ingredients,
        difficulty: item.difficulty,
        timeTaken: item.prepTimeMinutes + item.cookTimeMinutes,
        calorieCount: item.caloriesPerServing
    }));
    return rec;
}

function printAllRecipes(): void {
    fetchRecipesFromAPI().then((data) => {
        console.log(data);
    });
}

printAllRecipes();
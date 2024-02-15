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
    try {
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
    } catch (error) {
        console.error("Error fetching recipes:", error);
        throw error; 
    }
}

async function searchRecipes(query: string): Promise<Recipe[]> {
    try {
        const str = `https://dummyjson.com/recipes/search?q=${query}`;
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
    } catch (error) {
        console.error("Error searching recipes:", error);
        throw error; 
    }
}

function printAllRecipes(): void {
    fetchRecipesFromAPI()
        .then((data) => {
            console.log(data);
        })
        .catch((error) => {
            console.error("Error printing recipes:", error);
        });
}

printAllRecipes();

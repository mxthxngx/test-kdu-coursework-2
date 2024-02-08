let shoes = [ 
    {"type":"sneaker","price":2000,"color":"black","size":"10"},
    {"shoes":"badminton","price":3000,"color":"blue","size":"11"},
    ]

let shirts = [
    {"type":"sweatshirt","price":2000,"color":"black","size":"XS"},
    {"type":"hoodie","price":3000,"color":"blue","size":"S"},
]

let warehouse = shoes.concat(shirts)
warehouse.concat(shirts);

warehouse = warehouse.sort(function(a,b)
{
    return a.price - b.price;
})
console.log("Warehouse elements in sorted order")

console.log(warehouse)

let totalPrice = 0;
warehouse.forEach(element => totalPrice+=element.price)
console.log("Total price: "+totalPrice)


console.log("Blue elements:")
for(let i = 0; i <warehouse.length; i++)
{
    if(warehouse[i].color=="blue")
    {
        console.log(warehouse[i])
    }
}
let lst = [];
const btn = document.getElementById("add-btn");
btn.addEventListener("click", addTodo);

function addTodo() {
    const input = document.getElementById("input-todo");
    lst.push(input.value);
    const listItem = document.createElement('li');
    const spanItem = document.createElement('span');
    const spanWithBtn = document.createElement("button");
    spanWithBtn.className = "btn";
    spanWithBtn.innerText = "X ";
    spanWithBtn.style.color ="red";
    spanWithBtn.addEventListener("click", deleteTodo);
    spanItem.textContent = input.value;
    listItem.appendChild(spanItem);
    listItem.appendChild(spanWithBtn);
    document.getElementById("todo-list").appendChild(listItem); 
    input.value = ''; 
}

function deleteTodo(event) {
    const listItem = event.target.parentNode;
    const todoList = listItem.parentNode;
    todoList.removeChild(listItem);
}

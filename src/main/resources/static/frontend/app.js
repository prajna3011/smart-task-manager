const API = "http://localhost:8081/api/tasks";

function load() {
  fetch(API)
    .then((res) => res.json())
    .then((data) => {
      const list = document.getElementById("list");
      list.innerHTML = "";

      data.forEach((task) => {
        list.innerHTML += `
                <li>
                    ${task.title} - ${task.description}
                    <button onclick="del(${task.id})">Delete</button>
                </li>
                `;
      });
    });
}

function add() {
  const title = document.getElementById("title").value;
  const desc = document.getElementById("desc").value;

  if (title === "" || desc === "") {
    alert("Please enter title and description");
    return;
  }

  fetch(API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      title: title,
      description: desc,
      completed: false,
    }),
  }).then(() => {
    load();
  });

  document.getElementById("title").value = "";
  document.getElementById("desc").value = "";
}

function del(id) {
  fetch(API + "/" + id, {
    method: "DELETE",
  }).then(() => {
    load();
  });
}

// Load tasks on page load
load();

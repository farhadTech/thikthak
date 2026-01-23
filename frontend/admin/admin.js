const API_BASE = "https://api.thikthak.com";
const token = localStorage.getItem("token");

if (!token) {
  window.location.href = "/admin-login.html";
}

localStorage.setItem("token", data.token);

fetch(`${API_BASE}/admin/bookings`, {
  headers: {
    "Authorization": `Bearer ${token}`
  }
})
  .then(res => {
    if (res.status === 401) {
      alert("Unauthorized! Please login again.");
      window.location.href = "/admin-login.html";
    }
    return res.json();
  })
  .then(data => {
    bookings = data;
    renderBookings();
  });


fetch(`${API_BASE}/admin/bookings/${id}/status`
  , {method: "PUT"});

const table = document.getElementById("bookingTable");

function renderBookings () {
  table.innerHTML = "";
  bookings.forEach(b => {
    table.innerHTML += `
      <tr class="hover:bg-gray-50 transition">
        <td class="p-4">${b.name}</td>
        <td class="p-4">${b.service}</td>
        <td class="p-4">${b.date}</td>
        <td class="p-4">
          <span class="px-3 py-1 rounded-full text-sm
            ${b.status === "Pending" ? "bg-yellow-100 text-yellow-700" : "bg-green-100 text-green-700"}">
            ${b.status}
          </span>
        </td>
        <td class="p-4">
          <button onclick="toggleStatus(${b.id})"
            class="text-sm bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700">
            Toggle
          </button>
        </td>
      </tr>
    `;
  });
}

function toggleStatus (id) {
  fetch(`https://your-backend/admin/bookings/${id}/status`, {
    method: "PUT",
    headers: {
      "Authorization": `Bearer ${token}`,
      "Content-Type": "application/json"
    }
  })
    .then(() => {
      const booking = bookings.find(b => b.id === id);
      booking.status =
        booking.status === "Pending" ? "Completed" : "Pending";
      renderBookings();
    });
}

function logout () {
  localStorage.removeItem("token");
  window.location.href = "/admin-login.html";
}

document.getElementById('fetchCarsButton').addEventListener('click', fetchCars);

function fetchCars() {
  const apiUrl = 'http://localhost:8080/carRentals/getCars';

  // Make a GET request using the Fetch API
  fetch(apiUrl)
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      // Display the list of cars
      const carsList = document.getElementById('carsList');
      carsList.innerHTML = ''; // Clear the list before adding new items

      data.forEach(car => {
        const listItem = document.createElement('li');
        listItem.textContent = `${car.name} - ${car.count} available cars;
        carsList.appendChild(listItem);
      });
    })
    .catch(error => {
      console.error('Error fetching cars:', error);
    });
}

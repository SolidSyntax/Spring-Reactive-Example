const clearRestaurants = () => {
    console.log("Clear List");
    const restaurantList = document.getElementById("restaurantList");
    while (restaurantList.lastChild) restaurantList.removeChild(restaurantList.lastChild);
};

const findRestaurants = () => {
    clearRestaurants();
    console.log("Find restaurants");
    const restaurantList = document.getElementById("restaurantList");
    const cuisineInput = document.getElementById("cuisineInput");
    const cuisine = cuisineInput.options[cuisineInput.selectedIndex].value;
    const streamChecked = document.getElementById("streamInput").checked;
    const parameters = {
        url: '/restaurants?cuisine=' + cuisine,
        headers: {Accept: streamChecked ? "application/stream+json" : "application/json"}
    };

    oboe(parameters)
        .node('{name}', (restaurant) => {
            const row = createRowForRestaurant(restaurant);
            restaurantList.appendChild(row)
            //restaurantList.insertBefore(row,restaurantList.firstChild);
        }).done((restaurants) => {
        console.log("Found " + restaurants.length + " restaurants");
    });


};

const createRowForRestaurant = (restaurant) => {
    const row = document.createElement("tr");

    const tdName = document.createElement("td");
    const name = document.createTextNode(restaurant.name);
    tdName.appendChild(name);
    row.appendChild(tdName);

    const tdStars = document.createElement("td");
    const stars = document.createTextNode(restaurant.stars);
    tdStars.appendChild(stars);
    row.appendChild(tdStars);

    return row;
};



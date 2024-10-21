async function loadMap() {
    console.log("Загрузка карты...");

    let response = await fetch("http://localhost:8080/map/1");

    if (response.ok) {
        let json = await response.json();
        for (let i = 0; i < json.tiles.length; i++) {
            for (let j = 0; j < json.tiles[i].length; j++) {
                console.log("Tile at " + i + ", " + j + " visible " + json.tiles[i][j].visible);
                // $("#map").add = json.tiles[i][j].visible;
                const str = '<div class="tile wall" style="position: absolute; top: ' + (i * 50) +  'px; left: ' + (j * 50) + 'px; width: 50px; height: 50px; background-color: blue"></div>'
                $("#map").append(str);
            }
        }

    } else {
        alert("Ошибка HTTP: " + response.status);
    }

}


$(function () {
    $("#load-map").click(() => loadMap());
});
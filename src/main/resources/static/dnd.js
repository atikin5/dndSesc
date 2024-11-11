let styleSheet;
window.onload = function js() {
    styleSheet = document.styleSheets[0];
}

let idIndicator = 0;
let mouseIndicator = [
    {"class": "object",  "px": 50},
    {"class": "statistic", "px": 1}
];

async function loadMap() {
    console.log("Загрузка карты...");

    //let response = await fetch("http://localhost:8080/map/1");

    //if (response.ok)
    {
        let [json] = await Promise.all([{
            "tiles": [
                {"visible": true, "walkable": false, "type": "fire", "absX": 0, "absY": 100},
                {"visible": true, "walkable": true, "type": "stone", "absX": 50, "absY": 0},
                {"visible": true, "walkable": true, "type": "grass", "absX": 100, "absY": 0},
                {"visible": true, "walkable": true, "type": "fire", "absX": 0, "absY": 50},
                {"visible": true, "walkable": true, "type": "water", "absX": 50, "absY": 50},
                {"visible": true, "walkable": true, "type": "fire", "absX": 100, "absY": 50},
                {"visible": true, "walkable": true, "type": "stone", "absX": 0, "absY": 100},
                {"visible": true, "walkable": true, "type": "fire", "absX": 50, "absY": 100},
                {"visible": true, "walkable": true, "type": "fire", "absX": 100, "absY": 100}
            ],
            "characters": [
                {"type": "bard", "absX": 100, "absY": 100, "level": 1, "abilities": [{"str": 10}, {"dex": 10}, {"con": 10}, {"int": 10}, {"wis": 10}, {"cha": 10}],},
                {"type": "bard", "absX": 100, "absY": 400},
                {"type": "barbarian", "absX": 300, "absY": 200}
            ],
            "enemies": [
                {"type": "goblin", "absX": 100, "absY": 400}
            ],
            "otherObjects": [
                {"type": "table", "absX": 500, "absY": 400}
            ]
        }])
        for (let i = 0; i < json.tiles.length; i++) {
            let ind = 0;
            for (let j = 0; j < styleSheet.cssRules.length; j++) {
                if (styleSheet.cssRules[j].selectorText === '.' + json.tiles[i].type) {
                    ind = 1;
                }
            }
            if (ind === 0) {
                let selector = '.' + json.tiles[i].type;
                let style = "background-image: url(pictures/tiles/" + json.tiles[i].type + ".png)";
                styleSheet.insertRule(`${selector} { ${style} }`, styleSheet.cssRules.length);
            }
            const str = `<div class="tile ${json.tiles[i].type}" style="left: ${json.tiles[i].absX}px; top: ${json.tiles[i].absY}px;"></div>`
            $("#map").append(str);
        }
        console.log("Map done");
        for (let i = 0; i < json.characters.length; i++) {
            let ind = 0;
            for (let j = 0; j < styleSheet.cssRules.length; j++) {
                if (styleSheet.cssRules[j].selectorText === '.' + json.characters[i].type) {
                    ind = 1;
                }
            }
            if (ind === 0) {
                cssAddRule('.' + json.characters[i].type, "background-image: url(pictures/characters/" + json.characters[i].type + ".png)")
            }
            const str1 = `<div id="c${i}" class="object ${json.characters[i].type}" style="left: ${json.characters[i].absX}px; top: ${json.characters[i].absY}px;"></div>`
            $("#map").append(str1);
        }
        console.log("Characters done");
        for (let i = 0; i < json.enemies.length; i++) {
            let ind = 0;
            for (let j = 0; j < styleSheet.cssRules.length; j++) {
                if (styleSheet.cssRules[j].selectorText === '.' + json.enemies[i].type) {
                    ind = 1;
                }
            }
            if (ind === 0) {
                let selector = '.' + json.enemies[i].type;
                let style = "background-image: url(pictures/enemies/" + json.enemies[i].type + ".png)";
                styleSheet.insertRule(`${selector} { ${style} }`, styleSheet.cssRules.length);
            }
            const str1 = `<div id="e${i}" class="object ${json.enemies[i].type}" style="left: ${json.enemies[i].absX}px; top: ${json.enemies[i].absY}px;"></div>`
            $("#map").append(str1);
        }
        console.log("Enemies done");
        for (let i = 0; i < json.otherObjects.length; i++) {
            let ind = 0;
            for (let j = 0; j < styleSheet.cssRules.length; j++) {
                if (styleSheet.cssRules[j].selectorText === '.' + json.otherObjects[i].type) {
                    ind = 1;
                }
            }
            if (ind === 0) {
                let selector = '.' + json.otherObjects[i].type;
                let style = "background-image: url(pictures/otherObjects/" + json.otherObjects[i].type + ".png)";
                styleSheet.insertRule(`${selector} { ${style} }`, styleSheet.cssRules.length);
            }
            const str1 = `<div id="o${i}" class="object ${json.otherObjects[i].type}" style="left: ${json.otherObjects[i].absX}px; top: ${json.otherObjects[i].absY}px;"></div>`
            $("#map").append(str1);
        }
        console.log("All objects done");
        addListeners();
    }
    // else {
    //     alert("Ошибка HTTP: " + response.status);
    // }

}

$(function () {
    $("#load-map").click(() => loadMap());
});

function addListeners() {
    let objects = document.getElementsByClassName('object');
    [].forEach.call(objects, function (object) {
        object.addEventListener('mousedown', mouseDown, false);
        object.addEventListener('click', click, false);
    })
    window.addEventListener('mouseup', mouseUp, false);
}

function cssAddRule(selector, style) {
    styleSheet.insertRule(`${selector} { ${style} }`, styleSheet.cssRules.length);
}

function click(e){
    if (e.shiftKey) {
        statistic(e);
    }
}

function statistic(e) {
    let a = e.target.id;
    let b;
    if (a[0] === "c") {
        b = "characters";
    }
    if (a[0] === "e") {
        b = "enemies";
    }
    if (a[0] === "o") {
        b = "otherObjects";
    }
    console.log(`shiftClick on ${b}`);
    const str = `<div id = "s${a}"class="statistic ${b}" style="left: ${e.clientX}px; top: ${e.clientY}px;"><p>ererere</p></div>`;
    console.log("s" + a);
    $("#map").append(str);
    document.getElementById("s" + a).addEventListener('mousedown', mouseDown, false);
    document.getElementById("s" + a).addEventListener('mouseup', mouseUp, false);
}

function mouseUp() {
    window.removeEventListener('mousemove', divMove, true);
}

function mouseDown(e) {
    window.addEventListener('mousemove', divMove, true);
    window.moveObject = e.target;
}

function divMove(e) {
    let f;
    for (let i = 0; i < mouseIndicator.length; i++) {
        if (moveObject.classList.contains(`${mouseIndicator[i].class}`)) {
            f = mouseIndicator[i].px;
            break;
        }
    }
    console.log(f);
    moveObject.style.top = f * Math.trunc((e.clientY) / f) + 'px';
    moveObject.style.left = f * Math.trunc((e.clientX) / f) + 'px';
}

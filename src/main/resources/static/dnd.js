let styleSheet;
window.onload = function js() {
    styleSheet = document.styleSheets[1];
}

let mouseIndicator = [
    {"class": "object",  "px": 100},
    {"class": "statistic", "px": 1}
];

let statisticIndicator = [];

async function loadMap() {
    console.log("Загрузка карты...");

    //let response = await fetch("http://localhost:8080/map/1");

    //if (response.ok)
    {
        document.getElementById("load-map").remove();
        let [json] = await Promise.all([{
            "tiles": [
                {"visible": true, "walkable": false, "type": "fire", "absX": 0, "absY": 0},
                {"visible": true, "walkable": true, "type": "stone", "absX": 1, "absY": 0},
                {"visible": true, "walkable": true, "type": "grass", "absX": 2, "absY": 0},
                {"visible": true, "walkable": true, "type": "fire", "absX": 0, "absY": 1},
                {"visible": true, "walkable": true, "type": "water", "absX": 1, "absY": 1},
                {"visible": true, "walkable": true, "type": "fire", "absX": 2, "absY": 1},
                {"visible": true, "walkable": true, "type": "stone", "absX": 0, "absY": 2},
                {"visible": true, "walkable": true, "type": "fire", "absX": 1, "absY": 2},
                {"visible": true, "walkable": true, "type": "fire", "absX": 2, "absY": 2}
            ],
            "characters": [
                {"type": "bard", "absX": 2, "absY": 2, "level": 1, "abilities": [{"str": 10}, {"dex": 10}, {"con": 10}, {"int": 10}, {"wis": 10}, {"cha": 10}],},
                {"type": "bard", "absX": 2, "absY": 3},
                {"type": "barbarian", "absX": 6, "absY": 4}
            ],
            "enemies": [
                {"type": "goblin", "absX": 3, "absY": 4}
            ],
            "otherObjects": [
                {"type": "table", "absX": 5, "absY": 4}
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
            const str = `<div class="tile ${json.tiles[i].type}" style=" background-size: ${mouseIndicator[0].px}px; width: ${mouseIndicator[0].px}px; height: ${mouseIndicator[0].px}px; left: ${json.tiles[i].absX * mouseIndicator[0].px}px; top: ${json.tiles[i].absY * mouseIndicator[0].px}px;"></div>`
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
            const str1 = `<div id="c${i}" class="object ${json.characters[i].type}" style=" background-size: ${mouseIndicator[0].px}px; width: ${mouseIndicator[0].px}px; height: ${mouseIndicator[0].px}px; left: ${json.characters[i].absX * mouseIndicator[0].px}px; top: ${json.characters[i].absY * mouseIndicator[0].px}px;"></div>`
            $("#map").append(str1);
            let SI = `c${i}`;
            statisticIndicator[SI] = 0;
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
            const str1 = `<div id="e${i}" class="object ${json.enemies[i].type}" style=" background-size: ${mouseIndicator[0].px}px; width: ${mouseIndicator[0].px}px; height: ${mouseIndicator[0].px}px; left: ${json.enemies[i].absX * mouseIndicator[0].px}px; top: ${json.enemies[i].absY * mouseIndicator[0].px}px;"></div>`
            $("#map").append(str1);
            let SI = `e${i}`;
            statisticIndicator[SI] = 0;
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
            const str1 = `<div id="o${i}" class="object ${json.otherObjects[i].type}" style="background-size: ${mouseIndicator[0].px}px; width: ${mouseIndicator[0].px}px; height: ${mouseIndicator[0].px}px; left: ${json.otherObjects[i].absX * mouseIndicator[0].px}px; top: ${json.otherObjects[i].absY * mouseIndicator[0].px}px;"></div>`
            $("#map").append(str1);
            let SI = `o${i}`;
            statisticIndicator[SI] = 0;
        }
        console.log("All objects done");
        addListeners();
        $(function() {
            $('.object').draggable({
                grid: [mouseIndicator[0].px,mouseIndicator[0].px]
            });
        });
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
        object.addEventListener('click', click, false);
    })
}

function cssAddRule(selector, style) {
    styleSheet.insertRule(`${selector} { ${style} }`, styleSheet.cssRules.length);
}

function click(e){
    if (e.shiftKey) {
        statistic(e);
    }
}

let statId;
let cStatId;
let cBId;
let a;

function statistic(e) {
    a = e.target.id;
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
    const str = `<div id = "${statisticIndicator[a]}${a}"class="statistic ${b}" style="left: ${e.clientX}px; top: ${e.clientY}px;"><div id="c-b${statisticIndicator[a]}${a}" class="close-button" onClick="Del(this)"></div></div>`;
    $("#map").append(str);
    console.log(statisticIndicator);
    $(function() {
        $('.statistic').draggable({
            grid: [mouseIndicator[1].px,mouseIndicator[1].px]
        });
    });
    console.log(document.getElementById(`c-b${statisticIndicator[a]}${a}`))
    $( ".statistic" ).resizable(
        {
            autoHide: true,
            aspectRatio: true
        }
    )
    statisticIndicator[a] += 1;
}

function sortId(a, b, c) {
    for (let i = a; i < b; i++) {
        let d = document.getElementById(`${i+1}${c}`);
        console.log("d.id " + d.id)
        d.id = `${i}${c}`;
        console.log("d.id " + d.id)
        let db = document.getElementById(`c-b${i+1}${c}`);
        console.log("db.id " + db.id)
        db.id = `c-b${i}${c}`;
    }
    console.log("done, sort from " + a + " to " + b + " in stats of " + c);
}

function Del(el) {
    console.log(el)
    statId = el.parentNode.id;
    cStatId = statId.slice(0, Math.max(statId.indexOf("c"), statId.indexOf("e"),statId.indexOf("o")));
    cBId = statId.slice(Math.max(statId.indexOf("c"), statId.indexOf("e"),statId.indexOf("o")));
    $(`#${statId}`).remove();
    console.log("I delete: " + statId)
    console.log(statisticIndicator);
    statisticIndicator[a] -= 1;
    if (Number(cStatId) <= statisticIndicator[a]) {
        console.log("cStatId " + cStatId + " stat " + statisticIndicator[a] + " cBId " + cBId);
        sortId(Number(cStatId), statisticIndicator[a], cBId);
    }
}

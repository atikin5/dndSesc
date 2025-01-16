let styleSheet;
window.onload = function js() {
    styleSheet = document.styleSheets[1];
    loadWindow();
}

$(function() {

    $('#tabs').tabs();
    $('button').button();

});

function loadWindow() {
    const div = `
    <div id="campaigns-div">
        <button id="load-campaigns" style="visibility: visible" onClick="loadCampaigns()">Загрузить кампании</button>
        <div id="campaigns" style="visibility: visible">
            <table id="campaigns-table">
                <tr>
                    <th>Название</th>
                    <th>Код подключения</th>
                    <th>Дата создания</th>
                    <th>Дата начала</th>
                    <th>Дата окончания</th>
                    <th></th>
                </tr>
            </table>
        </div>
    </div>`
    $("#body").append(div);

}

let mouseIndicator = [
    {"class": "object",  "px": 100},
    {"class": "statistic", "px": 1}
];

let statisticIndicator = [];

async function loadCampaigns() {
    let response = await fetch("http://localhost:8080/campaign/page?page=0&size=10");
    if (response.ok)
    {
        let campaignsPage = await response.json();
        for (let i = 0; i < campaignsPage.content.length; i++) {
            const campaignTr = `
            <tr>
                <td>${campaignsPage.content[i].title}</td>
                <td>${campaignsPage.content[i].code}</td>
                <td>${campaignsPage.content[i].createdAt}</td>
                <td>${campaignsPage.content[i].startedAt}</td>
                <td>${campaignsPage.content[i].completedAt}</td>
                <td><button class="open-campaign" onClick="openCampaign(${campaignsPage.content[i].id})">Открыть</button></td>
            </tr>`;
            $("#campaigns-table").append(campaignTr);
        }
    }
}

let locPageSize = 10;
let locPagePage = 0;

async function openCampaign(id) {
    let responseCam = await fetch(`http://localhost:8080/campaign/${id}`);
    if (responseCam.ok) {
        $("#campaigns-div").remove();
        let campaign = await responseCam.json();
        let responseLoc = await fetch(`http://localhost:8080/location/page?campaignId=${campaign.id}&page=${locPagePage}&size=${locPageSize}`);
        if (responseLoc.ok) {
            let locationsPage = await responseLoc.json();
            const tabs = `
                <div id="tabs" class="campaign-tabs">
                    <ul>
                        <li><a href="#tab1">Локации</a>
                        <li><a href="#tab2">Существа</a>
                        <li><a href="#tab3">Персонажи</a>
                    </ul>
                    <div id="tab1"><table id="locations"><tr><th>Название</th><th></th></tr></table></div>
                    <div id="tab2"></div>
                    <div id="tab3"></div>
                </div>`
            $("#body").append(tabs);
            $(function() {
                $('#tabs').tabs();
                $('button').button();
            });
            for (let i = 0; i < locationsPage.content.length; i++) {
                console.log(1212)
                const locationsTr = `
                <tr>
                    <td>${locationsPage.content[i].name}</td>
                    <td><button class="open-location" onClick="openLocation(${locationsPage.content[i].id})">Перейти на локацию</button></td>
                </tr>`;
                $("#locations").append(locationsTr);
            }
            const creaturesTr = ``;
            const charactersTr = ``;
        }
        else {
            alert("Ошибка HTTP: " + responseLoc.status);
        }
    }
    else {
        alert("Ошибка HTTP: " + responseCam.status);
    }
}

async function openLocation(id) {
    console.log("Загрузка карты...");

    let response = await fetch(`http://localhost:8080/location/${id}/full`);

    if (response.ok)
    {
        console.log(111)
        let location = await response.json();
        // let [json] = await Promise.all(
        //     [{
        //     "tiles": [
        //         {"visible": true, "walkable": false, "type": "fire", "absX": 0, "absY": 0},
        //         {"visible": true, "walkable": true, "type": "stone", "absX": 1, "absY": 0},
        //         {"visible": true, "walkable": true, "type": "grass", "absX": 2, "absY": 0},
        //         {"visible": true, "walkable": true, "type": "fire", "absX": 0, "absY": 1},
        //         {"visible": true, "walkable": true, "type": "water", "absX": 1, "absY": 1},
        //         {"visible": true, "walkable": true, "type": "fire", "absX": 2, "absY": 1},
        //         {"visible": true, "walkable": true, "type": "stone", "absX": 0, "absY": 2},
        //         {"visible": true, "walkable": true, "type": "fire", "absX": 1, "absY": 2},
        //         {"visible": true, "walkable": true, "type": "fire", "absX": 2, "absY": 2}
        //     ],
        //     "characters": [
        //         {"type": "bard", "absX": 2, "absY": 2, "level": 1, "abilities": [{"str": 10}, {"dex": 10}, {"con": 10}, {"int": 10}, {"wis": 10}, {"cha": 10}],},
        //         {"type": "bard", "absX": 2, "absY": 3},
        //         {"type": "barbarian", "absX": 6, "absY": 4}
        //     ],
        //     "enemies": [
        //         {"type": "goblin", "absX": 3, "absY": 4}
        //     ],
        //     "otherObjects": [
        //         {"type": "table", "absX": 5, "absY": 4}
        //     ]
        // }])
        for (let i = 0; i < location.tiles.length; i++) {
            let ind = 0;
            for (let j = 0; j < styleSheet.cssRules.length; j++) {
                if (styleSheet.cssRules[j].selectorText === '.' + location.tiles[i].type) {
                    ind = 1;
                }
            }
            if (ind === 0) {
                let selector = '.' + location.tiles[i].type;
                let style = "background-image: url(pictures/tiles/" + location.tiles[i].type + ".png)";
                styleSheet.insertRule(`${selector} { ${style} }`, styleSheet.cssRules.length);
            }
            const str = `<div class="tile ${location.tiles[i].type}" style=" background-size: ${mouseIndicator[0].px}px; width: ${mouseIndicator[0].px}px; height: ${mouseIndicator[0].px}px; left: ${location.tiles[i].absX * mouseIndicator[0].px}px; top: ${location.tiles[i].absY * mouseIndicator[0].px}px;"></div>`
            $("#map").append(str);
        }
        console.log("Map done");
        for (let i = 0; i < location.dndCharacters.length; i++) {
            let ind = 0;
            for (let j = 0; j < styleSheet.cssRules.length; j++) {
                if (styleSheet.cssRules[j].selectorText === '.' + location.dndCharacters[i].type) {
                    ind = 1;
                }
            }
            if (ind === 0) {
                cssAddRule('.' + location.dndCharacters[i].type, "background-image: url(pictures/characters/" + location.dndCharacters[i].type + ".png)")
            }
            const str1 = `<div id="c${i}" class="object ${location.dndCharacters[i].type}" style=" background-size: ${mouseIndicator[0].px}px; width: ${mouseIndicator[0].px}px; height: ${mouseIndicator[0].px}px; left: ${location.dndCharacters[i].absX * mouseIndicator[0].px}px; top: ${location.dndCharacters[i].absY * mouseIndicator[0].px}px;"></div>`
            $("#map").append(str1);
            let SI = `c${i}`;
            statisticIndicator[SI] = 0;
        }
        console.log("Characters done");
        for (let i = 0; i < location.creatures.length; i++) {
            let ind = 0;
            for (let j = 0; j < styleSheet.cssRules.length; j++) {
                if (styleSheet.cssRules[j].selectorText === '.' + location.creatures[i].type) {
                    ind = 1;
                }
            }
            if (ind === 0) {
                let selector = '.' + location.creatures[i].type;
                let style = "background-image: url(pictures/enemies/" + location.creatures[i].type + ".png)";
                styleSheet.insertRule(`${selector} { ${style} }`, styleSheet.cssRules.length);
            }
            const str1 = `<div id="e${i}" class="object ${location.creatures[i].type}" style=" background-size: ${mouseIndicator[0].px}px; width: ${mouseIndicator[0].px}px; height: ${mouseIndicator[0].px}px; left: ${location.creatures[i].absX * mouseIndicator[0].px}px; top: ${location.creatures[i].absY * mouseIndicator[0].px}px;"></div>`
            $("#map").append(str1);
            let SI = `e${i}`;
            statisticIndicator[SI] = 0;
        }
        console.log("Creatures done");
        for (let i = 0; i < location.destructibleObjects.length; i++) {
            let ind = 0;
            for (let j = 0; j < styleSheet.cssRules.length; j++) {
                if (styleSheet.cssRules[j].selectorText === '.' + location.destructibleObjects[i].type) {
                    ind = 1;
                }
            }
            if (ind === 0) {
                let selector = '.' + location.destructibleObjects[i].type;
                let style = "background-image: url(pictures/otherObjects/" + location.destructibleObjects[i].type + ".png)";
                styleSheet.insertRule(`${selector} { ${style} }`, styleSheet.cssRules.length);
            }
            const str1 = `<div id="o${i}" class="object ${location.destructibleObjects[i].type}" style="background-size: ${mouseIndicator[0].px}px; width: ${mouseIndicator[0].px}px; height: ${mouseIndicator[0].px}px; left: ${location.destructibleObjects[i].absX * mouseIndicator[0].px}px; top: ${location.destructibleObjects[i].absY * mouseIndicator[0].px}px;"></div>`
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
    else {
        alert("Ошибка HTTP: " + response.status);
    }

}

$(function () {
    $("#load-map").click(() => openLocation());
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

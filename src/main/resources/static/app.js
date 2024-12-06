const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/websocket',
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/' + $("#serialNumber").val(), (message) => {
        showMessages(message.body);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#content").show();
    }
    else {
        $("#content").hide();
    }
    $("#messages").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendConnectMessage() {
    stompClient.publish({
        destination: "/app/" + $("#serialNumber").val(),
        body: JSON.stringify({ id: "2131" })
    });
}

function showMessages(message) {
    const obj = JSON.parse(message);
    $("#messages").append("<tr><td><pre>" + JSON.stringify(obj, null, 2) + "</pre></td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendConnectMessage());
});

// код bookmarklet'а для получения ссылки на страницу пространства Assembla, чтобы можно было обмениваться с коллегами

javascript:(function () {
    function unexpectedUrl(text) {
        alert("Unexpected url text format. " + text);
    }

    function linkToWiki(url, dataStart) {
        return url.substring(0, dataStart) + decodeURIComponent(url.substring(dataStart));
    }

    function linkToTickets(url, dataStart) {
        var data = url.substring(dataStart);
        var ticketNumStart = data.substring(0, 17) == "cardwall#/ticket:" ? 17 : 0;
        var ticketNumList = /[0-9]+/.exec(data.substring(ticketNumStart));
        if (!ticketNumList) {
            unexpectedUrl("Cant find ticket number in url");
            return null;
        }
        var ticketNum = ticketNumList[0];
        if (!ticketNum) {
            unexpectedUrl("Cant find ticket number");
            return null;
        }
        var ticketSummaryList = document.querySelectorAll("div.ticket-summary");
        if (ticketSummaryList.length == 0) {
            unexpectedUrl("Cant find ticket summary");
            return null;
        }
        var title = ticketSummaryList[0].children[0];
        if (title.nodeName.toLowerCase() != 'h1') {
            unexpectedUrl("Cant find ticket title");
            return null;
        }
        return "#" + ticketNum + " - " + title.innerText.trim() + "\n" + url.substring(0, dataStart) + ticketNum;
    }

    function parseLink() {
        var url = window.location.href;
        if (url.substring(0, 8) == "https://") {
            url = url.substring(8);
        }
        if (url.substring(0, 24) != "www.assembla.com/spaces/") {
            unexpectedUrl("Cant find assembla space prefix");
            return null;
        }
        var spaceName = /[-a-z0-9]+\//i.exec(url.substring(24))[0];
        if (!spaceName) {
            unexpectedUrl("Cant find assembla space name");
            return null;
        }
        var toolName = /[-a-z0-9]+\//i.exec(url.substring(24 + spaceName.length))[0];
        if (!toolName) {
            unexpectedUrl("Cant find assembla tool name");
            return null;
        }
        var dataStart = 24 + spaceName.length + toolName.length;
        switch (toolName) {
            case "tickets/":
                return linkToTickets(url, dataStart);
                break;
            case "wiki/":
                return linkToWiki(url, dataStart);
                break;
        }
        return null;
    }

    var toClipboard = function (text) {
        if (text) {
            window.prompt("Copy to clipboard:\n\n" + text + "\n\nCtrl+C, Enter", text);
        }
    };

    toClipboard(parseLink());
})();

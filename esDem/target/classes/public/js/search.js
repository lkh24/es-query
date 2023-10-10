document.addEventListener("DOMContentLoaded", function () {
    // 获取页面元素
    const searchInput = document.getElementById("nameInput");
    const searchResults = document.getElementById("searchResult"); // 修改 id 名称

    // 添加输入事件监听器
    searchInput.addEventListener("input", function () {
        // 获取用户输入的关键字
        const keyword = searchInput.value;

        // 发送搜索请求到服务器
        sendSearchRequest(keyword);
    });

    function displaySearchResults(results) {
        // 清空旧结果
        searchResults.innerHTML = "";

        // 遍历搜索结果数组，并创建相应的元素来显示结果
        results.forEach(function (result) {
            var resultElement = document.createElement("div");
            resultElement.textContent = result;

            // 添加点击事件处理程序
            resultElement.addEventListener("click", function () {
                searchInput.value = result; // 将结果填充到输入框中
            });

            searchResults.appendChild(resultElement);
        });
    }

    function sendSearchRequest(keyword) {
        var xhr = new XMLHttpRequest();
        var url = "http://localhost:8899/feed/getList";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = xhr.responseText;
                const searchResultsArray = JSON.parse(responseData);
                displaySearchResults(searchResultsArray);
            }
        };

        var data = { "keyword": keyword };
        xhr.send(JSON.stringify(data));
    }
});
<#include "base.ftlh">

<#macro title>message</#macro>
<#macro content>
 <div>
    <br>
    <div>
        <div>
            <div>
                <H2>
                    <@spring.message "admin"/>
                </H2>

                    <script type="text/javascript">
                        function waitmsg() {
                            console.log('call waitmsg()');
                            var xhr = new XMLHttpRequest();
                            xhr.open("GET", '/api/admin/get', true);
                            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                            xhr.onreadystatechange = function () {
                                if ((xhr.status == 200) && (xhr.readyState == 4)) {
                                    if (xhr.response != null) {
                                        var resp = JSON.parse(xhr.response);
                                        var tbl = document.getElementById("msgs");


                                        for (i = 0; i < resp.length; i++) {
                                            var row = tbl.insertRow();
                                            var cell0 = row.insertCell(0);
                                            cell0.innerHTML = resp[i]['userid'];
                                            var cell1 = row.insertCell(1);
                                            cell1.innerHTML = new Date().toLocaleString();
                                            var cell2 = row.insertCell(2);
                                            cell2.innerHTML = resp[i]['msg'];
                                        }
                                    }
                                    console.log('waiting');
                                    waitmsg();
                                }
                            }
                            xhr.send();
                        }

                        document.addEventListener('DOMContentLoaded', waitmsg());

                        function sendmsg(uid, user) {
                            console.log('sendmsg');
                            // var uid = document.getElementById("userid").value;
                            var msg = document.getElementById("msg").value;
                            console.log('uid='+uid+', msg='+msg );
                            var body = '{"userid":"'+uid+'","msg":"'+msg+'"}';

                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", '/api/admin/send/'+user, true);
                            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                            xhr.onreadystatechange = function () {
                                if ((xhr.status == 200) && (xhr.readyState == 4)) {
                                    // var rep = JSON.parse(xhr.response);
                                    var tbl = document.getElementById("msgs");
                                    var resp = JSON.parse(xhr.response);
                                    var row = tbl.insertRow();
                                    var cell0 = row.insertCell(0);
                                    cell0.innerHTML = resp['userid'];
                                    var cell1 = row.insertCell(1);
                                    cell1.innerHTML = new Date().toLocaleString();
                                    var cell2 = row.insertCell(2);
                                    cell2.innerHTML = resp['msg'];
                                }
                            }
                            xhr.send(body);
                        }
                    </script>

                    <div ><label for="msg"></label><input type="text" id="msg"/></div>
                         <br>

                    <button onclick="sendmsg('${userId}',  '${user}')"><@spring.message "send.message"/></button>

                    <h3><@spring.message "chat"/></h3>

                    <div>
                        <table id="msgs">
                            <tr>
                                <td><@spring.message "sender"/></td>
                                <td><@spring.message "time"/>  </td>
                                <td><@spring.message "message"/></td>
                            </tr>
                            <#list history as message>
                                <tr>
                                    <td>${message.sender.id}</td>
                                    <td>${message.time}</td>
                                    <td>${message.message}</td>
                                </tr>
                            </#list>
                        </table>
                    </div>
                </div>
            </div>
        <a href="/chats"><@spring.message "chats"/></a>

    </div>
</#macro>
<@main/>
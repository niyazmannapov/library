<#include "base.ftlh">

<#macro title>chats</#macro>
<#macro content>
    <form action="/chats" method="post" accept-charset="UTF-8">

        <div>
            <br>
            <div class="row">
                <div align="center">
                    <input type="text" name="chatName" placeholder="chat name">
                    <input type="submit" value="create new chat">
                </div>
            </div>
        </div>
    </form>


        <#list chats as chat>
                <a href="/chat/${chat.user.id}">${chat.user.email}</a>
                <br>
        </#list>
</#macro>
<@main/>
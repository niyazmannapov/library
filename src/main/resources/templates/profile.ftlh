<#include "base.ftlh">

<#macro title>Profile</#macro>

<#macro content>
    <div>
            <br>
            <div>
                <div>
                     <div>
                         <h1>${user.email}</h1>
                         <form action="/files" method="get">
                             <div>
                                 <button type="submit">Загрузить файл!</button>
                             </div>
                         </form>
                         <form action="/files/file" method="post">

                             <div>
                                 <input type="text" name="filename">
                             </div>
                             <div>
                                 <button type="submit">Получить загруженный файл</button>
                             </div>

                             <br>
                             <img src="${file!}" width="200"/>
                         </form>
                         <input type="submit"value="Chats" onclick="location.href='/chats'">
                     </div>
                </div>
            </div>

    </div>
</#macro>

<@main/>
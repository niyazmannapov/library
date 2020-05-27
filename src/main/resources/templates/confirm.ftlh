<#include "base.ftlh">

<#macro title>Confirmation</#macro>

<#macro content>
    <form action="/confirm" method="post">

        <div>
            <br>
            <div>
                <span><h1>Confirmation</h1></span>
                <br>
                <br>
                <br>
                <div>
                    <label for="inputUsername">Confirmation code</label>
                    <input type="text" name="code">
                </div>
                <br>
                <button type="submit" value="save">Confirm</button>
            </div>
        </div>
    </form>
</#macro>

<@main/>
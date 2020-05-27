<#include "base.ftlh">

<#macro title>Дом</#macro>

<#macro content>
    <div>
        <form action="/signIn" method="get">
            <div>
                <button type="submit">Войти</button>
            </div>
        </form>
        <form action="/signUp" method="get">
            <div>
                <button type="submit">Зарегистрироваться</button>
            </div>
        </form>
    </div>
</#macro>

<@main/>
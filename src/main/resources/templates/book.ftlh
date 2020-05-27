<!DOCTYPE html>
<#include "base.ftlh">

<#macro title>Home</#macro>

<#macro content>
    <div>

        Автор: ${book.author.firstName} ${book.author.lastName}
        <br>
        Название: ${book.name}
        <br>
        ${book.text}
    </div>
</#macro>

<@main/>
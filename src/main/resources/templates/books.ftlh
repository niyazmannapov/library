<!DOCTYPE html>
<#include "base.ftlh">

<#macro title>Home</#macro>

<#macro content>
    <div>
        <@spring.bind "bookForm"/>
        <form method="POST" action="/books">
            Название книги: <@spring.formInput "bookForm.name" "class='form-control'"/> <br/>
            <@spring.showErrors "<br>","error"/>
            Имя автора: <@spring.formInput "bookForm.authorFirstName" "class='form-control'"/> <br/>
            <@spring.showErrors "<br>","error"/>
            Фамилия автора: <@spring.formInput "bookForm.authorLastName" "class='form-control'"/> <br/>
            <@spring.showErrors "<br>","error"/>
            Текст книги: <@spring.formTextarea "bookForm.text" "class='form-control'"/> <br/>
            <@spring.showErrors "<br>","error"/>
            <button type="submit">Отправить</button>
        </form>

        <#list books as book>
            Автор: ${book.author.firstName} ${book.author.lastName} Книга: <a
                href="/book?bookId=${book.id}">${book.name}</a>
            <br>
        </#list>
    </div>
</#macro>

<@main/>

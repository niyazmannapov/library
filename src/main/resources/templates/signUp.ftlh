<#include "base.ftlh">

<#macro title>Registration</#macro>

<#macro content>
    <form action="/signUp" method="post">
        <div>
            <br>
                <div>
                    <div align="center">
                        <div>
                            <span><h1><@spring.message "signUp.label.registration"/></h1></span>
                            <br>
                            <br>
                            <br>

                            <div class="form-group">
                                <label for="exampleInputEmail1"><@spring.message "page.email"/><span class="required">*</span></label>
                                <input type="text" id="exampleInputEmail1" aria-describedby="emailHelp"
                                       placeholder="<@spring.message "page.placeholder.email"/>" name="email">
                            </div>
                            <div>
                                <label for="exampleInputPassword1"><@spring.message "page.password"/><span class="required">*</span></label>
                                <input type="password" class="form-control" id="exampleInputPassword1"
                                       placeholder="<@spring.message "page.placeholder.password"/>"
                                       name="password">
                            </div>
                            <br>

                            <input type="submit" class="btn btn-danger" value="<@spring.message 'page.registration.button.submit'/>"/>
                            <label>
                                <input class="checkbox" name="rememberme" value="" type="checkbox">
                                <span><@spring.message "page.remember"/></span>
                            </label>

                            <a href="/signIn" class="already"><@spring.message "page.registration.login"/></a>
                        </div>
                    </div>
                </div>

        </div>

    </form>

</#macro>

<@main/>
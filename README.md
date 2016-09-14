# OAUTH2StepsAndroid
Module for android that allows using OAUTH2 login via web using a custom webView.

## Getting Started
Just clone this repo and open it with ANDROID STUDIO. This is a library module, you can't run it alone! It only will run
adding it in an android application project as a module

### Installing
This module need the apt dependency. Just add this line in you build.grade project level:
```
classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
```

Then import this project as module into your module and add the dependency to your app package via Project Structure

### How-to
Create an object that implement LoginConstants interface. That allows you to override OAUTH2 values.
Create an OAUTH2Config object, set your configuration for the oauth2web and add your LoginConstantsImplementation:
```
OAUTH2Config config = new OAUTH2Config();
  config.setApiAuthBase("your_base_url");
  config.setClient_id("your_client");
  config.setRedirect_uri("your_redirect_uri");
  config.setLoginConstants("your_login_constats_implementation);
  config.setCode("your_type_code");
  config.setTokenEndpoint("your_token_endpoint");
```

Start the webview activity with the configuration awaiting for a result:
```
startActivityForResult(OAUTH2Activity.getCallingIntent(this,config),OAUTH2Activity.SUCESS_RESULT);
```

In the onActivityResult, you will be able to get the token autenticator:
```
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(resultCode == OAUTH2Activity.SUCESS_RESULT) {
        AccessToken accessToken = (AccessToken) data.getSerializableExtra(OAUTH2Activity
                .TOKEN_RESULT);
    }else{
        //throw failure
    }
}
```

## Built With

* Android Studio

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Uri Abad** - *Development* - [uriAbad](https://github.com/uriAbad)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the WIP License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

WIP.....



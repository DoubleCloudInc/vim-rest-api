# vim-rest-api

This project includes the scripts and clients for the DoubleCloud REST API for vSphere, which provides FULL REST API support for MANY vSphere servers. Currently, we have samples in Java, Python, PowerShell, and curl based shell scripts.

To get the APIs server, please visit http://www.doublecloud.net/downloads.php


# API Overview

The REST API provides complete functionalities as described in the offical VMware vSphere APIs. It also scales to multiple vCenter and/or ESXi servers so that you can manage all your vCenter and ESXi servers of all versions with single APIs.

For more details about the API, please check out the tech talk via vBrownBag on YouTube.


# Get Started

You will need to download the API server from this link: http://www.doublecloud.net/downloads.php

## Running API Server
To run the server, you simply double click on the jar file. The Web GUI will be started automatically in your system default browser in a second. By default, the server listens to 8080 with access key as admin and secure key as doublecloud.

To change the settings, you want to run it with the command line as follows. The detailed command arguments are very simple and decribed in the console output.

```
java -jar vimrest-1.0.jar [port] [<access-key> <secret-key>]
```

## Trying the Web GUI
The Web GUI serves two purposes: documentation and testing. It lists all the resources and available URLs organized alphabetically. You will find it very helpful to browse the page to get a sense what are there for you.

### Adding New vCenter
To add a new vCenter server, you want to use the ServiceInstance resource with the POST method with server IP address, username and password. You may be prompted for API access key and secure key. You can repeat this for many vCenter or ESXi servers.

### Listing VMs
Now you can try out the GET of VirtualMachine resource as well as other resources, for example, HostSystem, Networks. A single click will get you all the resources from all the vCenter and ESXi servers. These resources include name, id, and their inventory path.

### Power On VMs
If you want to change something, you will use the POST method on related resources. For a quick example, you can power on a virtual machine from last step.


# API URL Patterns

There are more than 100 types of resources in the system. For each type of resource, two methods are supported: GET and POST. To retrieve data from the vSphere, you use GET. To change anything there, you use POST. No other methods are used for simplicity.

Because the APIs has built-in support for multiple vCenter or ESXi servers, each resouce is identified with two elements: the IP address or FQDN of the vSphere, and the managed object reference ID within that vSphere context as follows:
```
<ip>:<moid>
10.18.19.10:vm-123
vcenter01.example.com:host-321
```

## List Resources
The following URL retrieves all the resources of certain types from all or certain optional roots. When root is not provided, it defaults to all. When the root query parameters are there, it will list all the resources under these root resources.
```
GET http://<api-server-ip-or-fqdn>:<port>/<resource-type-name>?root=<id>&root=<id>
```
Using the root resource id helps to scope down resources under these roots. For example, you can list VMs in a certain data centers within one or more vSphere environmments easily. The moid of the root can be omitted and defaults to all of the vSphere.
```
GET http://10.18.19.10:8080/VirtualMachine?root=vCenter1:group-d4&vCenter2
```

## Get Details of Resource
Once you get the id of a resource (most likely from the above listing), you can retrieve all or partial details about the resource using the following URLs
```
GET http://<api-server-ip-or-fqdn>:<port>/<resource-type-name>/<resource-id>/<optional-property-names>
```

The property names are names of properties delimited with comma. They are optional. When not provided, it defaults to all properties.

## Take Actions
To manage the target vSphere, you can POST against certain resource, for example, power on a virtual machines, etc. The URL is like this:
```
POST http://<api-server-ip-or-fqdn>:<port>/<resource-type-name>/<resource-id>/<action-name>
```
Some of the actions do not need request body, but most do. You want to provide the JSON data in the request body to the REST request. For the power on action for VM, you want to provide the following request body. Notice that the host is really optional. If you want to omit it, you can assign it to null as the second example.
```
{"host":{"type":"HostSystem","val":"host-123"}}
or
{"host":null}
```

For singleton resources (only one instance within one vCenter or ESXi context), you can omit the manaaged object ID and use only vSphere IP or FQDN.



# FAQ

## How hard is it to get started with the REST APIs?
The REST APIs is very easy to get started with. We have included a Web GUI, with which you can easily try out the APIs. If you have used the vSphere APIs, the learning curves is zero. If you have never used vSphere APIs, the initial learning curve is also very low. When you need more advanced features like changing VM configuration, the learning curve becomes higher but definitely not to the level of the vSphere API itself.

## What can I do with the REST APIs?
You can do almost everything as you can with the VMware vSphere Web Client.

## Can we use the API with existing installations?
Yes. Whatever installation of vCenter or vSphere is fine.

## Do we have to install anything in vCenter or ESXi?
No. The API server is written in Java thus can run everywhere including your desktop for testing purpose. You can install the server within vCenter, but it's not recommended because it may complicate your support contract with VMware. More importantly, the APIs is designed to support multiple vCenter servers thus not best use of the APIs if so.

## Which version of vSphere does it support?
All the versions of vSphere VMware has shipped. The API supports mixed versions of vSphere as well, so you don't have to upgrade just because of the APIs.

## How many vCenter servers or ESxi server can it support?
There is no limit on the number except the resouce required to run the API server. In reality, you won't hit the limit.

## What languages are supported?
Any programming languages that support HTTP, which essentially means every modern programming and scripting languages. Included in this project as REST clients are Java, Shell scripts, Python, PowerShell. Contributions are very welcome.

## Is it related to VMware vSphere REST APIs?
No, but it's related to the offical vSphere APIs which is defined as SOAP service - the DoubleCloud REST APIs shares the same object model of the vSphere APIs, and adds much more features on scalability and usability.

## Do I have to switch from vijava to this REST APIs?
You don't have to. Both SOAP and REST work well. If you have more than one vCenter or ESXi to manage, using this REST APIs will make life easier due to its built-in support.
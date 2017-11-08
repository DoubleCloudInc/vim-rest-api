$addvc = @{uri = 'http://localhost:8080/api/ServiceInstance';
                   Method = 'POST';
                   Headers = @{Authorization = 'Basic ' + [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("admin:doublecloud"));
                   Body = '{"ip": "192.168.0.200","username": "root", "password":"doublecloud"}';
           }
   }
invoke-restmethod @addvc

$listvm = @{uri = 'http://localhost:8080/api/VirtualMachine';
                   Method = 'GET';
                   Headers = @{Authorization = 'Basic ' + [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("admin:doublecloud"));
           }
   }
invoke-restmethod @listvm
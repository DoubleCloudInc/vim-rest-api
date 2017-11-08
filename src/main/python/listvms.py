import urllib2

baseUrl = 'http://localhost:8080/api'
authinfo = urllib2.HTTPPasswordMgrWithDefaultRealm()
authinfo.add_password(None, baseUrl, 'admin', 'doublecloud')
handler = urllib2.HTTPBasicAuthHandler(authinfo)
client = urllib2.build_opener(handler)
# urllib2.install_opener(client)

page = baseUrl + '/ServiceInstance'
client.open(page, "{\"ip\": \"192.168.0.200\",\"username\": \"root\", \"password\":\"doublecloud\"}")

listvms = baseUrl + '/VirtualMachine'
f = client.open(listvms)
vms = f.read()
print vms


# chaosMonkey
Chaos Monkey is a software tool that was developed by Netflix engineers to test the resiliency and recoverability of their Amazon Web Services.
Chaos Monkey works on the principle that the best way to avoid major failures is to fail constantly

# Major Components of Chaos Monkey
- Watchers  
- Assaults

# Watcher

Watcher will scan below spring annotation in the code which can be made configurable.

- Component
- Controller
- RestController
- Service
- Repository

# Assault

- Latency Assault – adds random latency to the request
- Exception Assault – throws random Runtime Exception
- AppKiller Assault – The app dies

# Configuration
- spring.profiles.active=chaos-monkey
- chaos.monkey.enabled=true
- chaos.monkey.assaults.latencyActive=true
- chaos.monkey.assaults.latencyRangeStart=1000
- chaos.monkey.assaults.latencyRangeEnd=15000
- chaos.monkey.assaults.exceptionsActive=false
- chaos.monkey.assaults.killApplicationActive=false
- chaos.monkey.watcher.controller=true
- chaos.monkey.watcher.restController=true
- chaos.monkey.watcher.service=true
- chaos.monkey.watcher.repository=true
- chaos.monkey.assaults.level=1
- management.endpoint.chaosmonkey.enabled=true
- management.endpoint.chaosmonkeyjmx.enabled=true
- management.endpoints.web.exposure.include=*
- management.server.port=8888



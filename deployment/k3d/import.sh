docker build -t discovery-service:latest ../../DiscoveryService
docker build -t auth-service:latest ../../AuthServer
docker build -t task-service:latest ../../TaskService
docker build -t gateway:latest ../../ApiGateway
docker build -t notification-service:latest ../../NotificationService
k3d image import auth-service:latest -c mycluster
k3d image import discovery-service:latest -c mycluster
k3d image import gateway:latest -c mycluster
k3d image import notification-service:latest -c mycluster
k3d image import task-service:latest -c mycluster
kubectl apply -f ../k3d/manifest

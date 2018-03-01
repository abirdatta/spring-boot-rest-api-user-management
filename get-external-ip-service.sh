external_ip=""
while [ -z $external_ip ]; do
    sleep 10
    external_ip=$(kubectl get svc user-api --template="{{range .status.loadBalancer.ingress}}{{.ip}}{{end}}")
done
echo "$external_ip"

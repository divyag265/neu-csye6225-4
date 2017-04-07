

-The first step of the certificate managing after the purchase is the process of activation.
  For activation you need to generate the Certificate Signing Request (CSR) through IAM.
-Certificate Signing Request is a small encrypted piece of text which contains information about a certificate applicant and the domain name to secure. 
-OpenSSL. This tool is designed to generate a private key and CSR.
-AWS command-line interface (CLI). It is used to upload certificates to AWS.
-The openssl command to generate a private key is:

openssl genrsa 2048 > private-key.pem

-The CSR is generated based on the private key. The following command is used for the CSR creation:

openssl req -new -key private-key.pem -out csr.pem

-Put all information needs to be filled in.
-In the output you will see the CSR in plain text. This text should be saved and used for the certificate activation.
Once the certificate is issued by the Certificate Authority, you can proceed with its installation.

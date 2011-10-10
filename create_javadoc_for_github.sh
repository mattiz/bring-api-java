#!/bin/sh

if [ ! -d 'pages' ]; then
    echo "Checking out gh-pages branch into pages/"
    git clone --shared --no-checkout . pages
    cd pages/
    git checkout gh-pages
    git remote set-url origin git@github.com:bring/bring-api-java.git
    cd ..
fi

mvn site
cp -R target/site/apidocs pages/
cd pages/

echo ""
echo ""
echo "----------------------------------------------"
echo "The gh-pages branch is in directory pages/"
echo "Rename pages/apidocs to current version"
echo "eg. mv pages/apidocs pages/1.0.0"
echo ""
echo "Remember to commit and push changes"
echo ""

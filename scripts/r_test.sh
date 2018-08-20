cd ./R
# R -e "devtools::install_deps('.')"
R CMD build .
R CMD check sparkLibrary*.tar.gz
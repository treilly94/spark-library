from setuptools import setup, find_packages


# get the version number
VERSION_FILE = '_version.txt'
with open(VERSION_FILE, 'r') as versionfile:
    version_string = versionfile.read().replace('\n', '')
if len(version_string) == 0:
    raise RuntimeError("Unable to find version string in %s." % (VERSION_FILE,))

setup(
    name='spark-library',
    version=version_string,
    packages=find_packages(),
    url='https://github.com/treilly94/spark-library',
    license='MIT License',
    author='Thomas Reilly',
    author_email='treilly94@live.com',
    include_package_data=True,
    keywords=['spark', 'library'],
    description='A polyglot spark methods library using Scala, Java and Python.',
)

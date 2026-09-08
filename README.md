# Libgraph Installation Steps on Ubuntu 22.04

> Only guile 2.2 and 3.0 — For CG Lab

## 1. Open the terminal, copy the following commands one by one and execute

```bash
sudo apt-get update
```

```bash
sudo apt-get install libsdl-image1.2 libsdl-image1.2-dev guile-2.2 guile-2.2-dev
```

> **Note:** Ubuntu 22.04 ships with guile-3.0 by default. If `guile-2.2` and `guile-2.2-dev` are not found, check availability first with:
> ```bash
> apt-cache search guile-2.2
> ```
> If unavailable, you can substitute `guile-3.0` and `guile-3.0-dev` in this command and in the `pkg-config` flags below (replace `guile-2.2` with `guile-3.0`).

```bash
wget http://download.savannah.gnu.org/releases/libgraph/libgraph-1.0.2.tar.gz
```

```bash
tar -xzvf libgraph-1.0.2.tar.gz
```

```bash
cd libgraph-1.0.2
```

Run the following as a single copy-paste block (do not split into separate lines, and ensure there is no trailing whitespace after each `\`):

```bash
CPPFLAGS="$CPPFLAGS $(pkg-config --cflags-only-I guile-2.2) -fcommon" \
CFLAGS="$CFLAGS $(pkg-config --cflags-only-other guile-2.2) -fcommon" \
LDFLAGS="$LDFLAGS $(pkg-config --libs guile-2.2)" \
./configure
```

```bash
make && sudo make install
```

```bash
sudo cp /usr/local/lib/libgraph.* /usr/lib
```

> **Note:** Confirm the files actually exist at `/usr/local/lib/libgraph.*` after `make install` before running this copy command. If nothing matches, check the `make install` output for the actual install path.

---

## 2. Open the terminal and follow the next steps to write a program

**Write:**
```bash
gedit cg1.cpp
```

**Compile:**
```bash
g++ -o cg1 cg1.cpp -lgraph
```

**Execute:**
```bash
./cg1
```

---

## CG Program for Reference

[https://github.com/hash-bash/Computer-Graphics-Programs/tree/master/Libgraph](https://github.com/hash-bash/Computer-Graphics-Programs/tree/master/Libgraph)

# mmtc-webapp-ui

Stack:
- Vue 3
- Nuxt 3
- NuxtUI 4

## Development

### Requirements

- [Node](https://nodejs.org/en/download)
  - Tested to work with node v22.19.0 and v23.3.0
- [PNPM](https://get.pnpm.io)
  - Tested to work with pnpm 10.18.1, 11.9.0, 12.5.1
  - **Important**: Node.js v23+ requires pnpm 12.5.1 or later
  
### Installing pnpm

If pnpm is not installed, install it globally via npm:
```bash
npm install -g pnpm@latest
```

Verify installation:
```bash
pnpm --version
```

## Building

```sh
# first time only:
pnpm install

# to start a development server with hot reloading:
pnpm dev

# to generate static bundle:
# npx nuxt generate
pnpm exec nuxt generate
```

## Notes

This web application was originally made using the following:
- https://nuxt.com/docs/3.x/getting-started/installation

Followed some guidance in: https://pnpm.io/supply-chain-security:
- With pnpm 10+, postinstall scripts are blocked by default
- Set `minimumReleaseAge` and `blockExoticSubdeps`

## Troubleshooting

### "A problem occurred starting process 'command 'pnpm'"

This error occurs when pnpm is not installed or not in your PATH. Install it globally:
```bash
npm install -g pnpm@latest
```

### "ERR_UNKNOWN_BUILTIN_MODULE: No such built-in module: node:sqlite"

This error indicates pnpm version incompatibility with your Node.js version. Update to the latest pnpm:
```bash
npm install -g pnpm@latest
```

For Node.js v23+, you must use pnpm 12.5.1 or later.

## Updating the build and its dependencies

- Updating pnpm itself:
  - `pnpm self-update <version>`
    - After this, if you get a `ERR_PNPM_PUBLIC_HOIST_PATTERN_DIFF` error, you may need to recreate the modules directory via `pnpm install`
- Updating pnpm-managed dependencies:
  - Check for packages with updates within the version range specified in `package.json`: `pnpm outdated` and `pnpm outdated --compatible`
  - Update to the latest version of deps compatible with the contents of `package.json`: `pnpm update`
  - (or) update to the absolute latest version (crossing major versions, and even perhaps updating `package.json`): `pnpm update --latest`

